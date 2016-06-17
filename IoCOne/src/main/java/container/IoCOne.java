package container;

import annotation.UseIoCOne;
import com.sun.istack.internal.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class IoCOne implements IoCInterface {

    private static Map<Class, Class> diMap = new HashMap<>();

    @Override
    public void register(Class tInterface, Class tRealisation) {
        diMap.put(tInterface, tRealisation);
    }

    public Object resolve(@NotNull Class object) {
        Object result = null;
        Constructor annotatedConstructor = null;
        Class[] parameterTypes = null;
        Object[] parameters = null;

        try {
            annotatedConstructor = findAnnotatedConstructor(object);
            if (annotatedConstructor.getGenericParameterTypes().length == 0) return object.newInstance();
            parameterTypes = annotatedConstructor.getParameterTypes();
            parameters = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                Class paramRealisation = findParameterRealisation(parameterTypes[i]);
                parameters[i] = resolve(paramRealisation);
            }
            result = annotatedConstructor.newInstance(parameters);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Constructor findAnnotatedConstructor(Class object) throws NoSuchMethodException {
        for (Constructor constructor : object.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(IOC_ANNOTATION)) {
                return constructor;
            }
        }
        return object.getConstructor();
    }

    private Class findParameterRealisation(Class parameterType) {
        Class parameterTypeRealisation = diMap.get(parameterType);
        if (parameterTypeRealisation == null) {
            parameterTypeRealisation = parameterType;
        } else parameterTypeRealisation = parameterTypeRealisation.asSubclass(parameterType);
        return parameterTypeRealisation;
    }
}
