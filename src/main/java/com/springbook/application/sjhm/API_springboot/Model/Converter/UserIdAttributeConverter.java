package com.springbook.application.sjhm.API_springboot.Model.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

import com.springbook.application.sjhm.API_springboot.Model.User.UserId;

@Converter(autoApply = true)
public class UserIdAttributeConverter implements AttributeConverter<UserId, UUID> {
    @Override
    public UUID convertToDatabaseColumn(UserId attribute) {
        return attribute.getId();
    }

    @Override
    public UserId convertToEntityAttribute(UUID dbData) {
        return new UserId(dbData);
    }
}