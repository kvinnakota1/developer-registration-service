package test.codingassignment.developer.registration.helper;

import java.util.HashMap;
import java.util.Map;

public enum ProductsEnum {

    CISCOWEBEX(100L, "Cisco Webex"),
    CISCOSYSTEMS(101L, "Cisco Systems"),
    CISCOROUTERS(102L, "Cisco Routers"),
    CISCOSWITCHES(103L, "Cisco Switches");


    private static final Map<Long, ProductsEnum> byId = new HashMap<Long, ProductsEnum>();
    static {
        for (ProductsEnum e : ProductsEnum.values()) {
            if (byId.put(e.getId(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getId());
            }
        }
    }

    public static ProductsEnum getById(Long id) {
        return byId.get(id);
    }


    private String name;

    private Long id;

    ProductsEnum(Long id, java.lang.String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
