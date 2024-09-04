package com.cydeo.util;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class RoleDTODeserializer extends JsonDeserializer<RoleDTO> {

    private final RoleService roleService;

    public RoleDTODeserializer(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String roleDescription = node.asText();

        return roleService.readByDescription(roleDescription);

    }

}
