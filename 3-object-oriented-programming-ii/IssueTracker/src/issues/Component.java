package issues;

public class Component {
    private String name;
    private String shortName;

    public String getShortName() {
        return shortName;
    }

    public Component(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Component component = (Component) obj;
        return this.name.equals(component.name) && this.shortName.equals(component.shortName);
    }
}