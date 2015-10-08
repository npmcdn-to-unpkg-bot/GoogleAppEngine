package com.ypg.model.search;

import com.framework.util.collection.DataCollection;
import com.framework.util.search.EntityField;
import com.framework.util.search.EntityFieldType;
import com.framework.util.search.EntitySearchMap;

public class UserSearchMap implements EntitySearchMap {

    private DataCollection<String, EntityField> fields;

    public DataCollection<String, EntityField> getFields() {
        fields = new DataCollection<String, EntityField>();
        // generator:fields
		fields.put("id", new EntityField("Id", "id", EntityFieldType.INT));
		fields.put("name", new EntityField("Name", "name", EntityFieldType.STRING));
		fields.put("passw", new EntityField("Passw", "passw", EntityFieldType.STRING));
		fields.put("registration", new EntityField("Registration", "registration", EntityFieldType.DATE));


        return fields;
    }
    
}

