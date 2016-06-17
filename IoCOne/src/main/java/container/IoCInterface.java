package container;


import annotation.UseIoCOne;

public interface IoCInterface {

    static final Class IOC_ANNOTATION = UseIoCOne.class;
    public void register(Class tInterface, Class tRealisation);
    public Object resolve(Class tInterface);
}
