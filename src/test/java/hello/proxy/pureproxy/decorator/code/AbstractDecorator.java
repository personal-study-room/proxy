package hello.proxy.pureproxy.decorator.code;

public abstract class AbstractDecorator implements Component{

    protected Component component;

    public AbstractDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        return null;
    }
}
