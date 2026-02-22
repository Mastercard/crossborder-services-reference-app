package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;



@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Field {
    private String fieldName;
    private String mandatory;
    private String validationPattern;
    private String additionalFieldId;
    private String additionalFieldName;
    private String specialNotes;
    private Date effectiveDate;
    private Date lastUpdatedTime;
    private String lastUpdateComments;
    private List<SupportedValues> supportedValues;

    private Field(FieldBuilder fieldBuilder) {
        this.additionalFieldId=fieldBuilder.additionalFieldId;
        this.additionalFieldName=fieldBuilder.additionalFieldName;
        this.fieldName=fieldBuilder.fieldName;
        this.mandatory=fieldBuilder.mandatory;
        this.validationPattern=fieldBuilder.validationPattern;
        this.specialNotes=fieldBuilder.specialNotes;
        this.effectiveDate = fieldBuilder.effectiveDate;
        this.lastUpdatedTime = fieldBuilder.lastUpdatedTime;
        this.lastUpdateComments = fieldBuilder.lastUpdateComments;
        this.supportedValues=fieldBuilder.supportedValues;

    }

    public Field() {

    }

    public String getSpecialNotes() {
        specialNotes= specialNotes!=null?specialNotes.
                replaceAll("(?i)\\[\\s*(Payment|Quote)\\s+(Req|Resp)\\s*-\\s*[MO]\\s*\\]\\s*", "")
                .replaceAll("\\s+", " ") // Replace multiple spaces/newlines with a single space
                .trim():null;
        specialNotes= specialNotes==null?"":specialNotes;
        if (specialNotes.isEmpty()){
            specialNotes=null;
        }
        return specialNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Field field = (Field) o;
        return Objects.equals(fieldName, field.fieldName) && Objects.equals(additionalFieldId, field.additionalFieldId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, additionalFieldId);
    }

    public static class FieldBuilder {
        private String fieldName;
        private String mandatory;
        private String validationPattern;
        private String additionalFieldId;
        private String additionalFieldName;
        private String specialNotes;
        private Date effectiveDate;
        private Date lastUpdatedTime;
        private String lastUpdateComments;
        private List<SupportedValues> supportedValues;

        public FieldBuilder setMandatory(String mandatory) {
            this.mandatory = mandatory;
            return this;
        }

        public FieldBuilder setFieldName(String fieldName) {
            this.fieldName = fieldName;
            return this;
        }

        public FieldBuilder setValidationPattern(String validationPattern) {
            this.validationPattern = validationPattern;
            return this;
        }

        public FieldBuilder setAdditionalFieldId(String additionalFieldId) {
            this.additionalFieldId = additionalFieldId;
            return this;
        }

        public FieldBuilder setAdditionalFieldName(String additionalFieldName) {
            this.additionalFieldName = additionalFieldName;
            return this;
        }

        public FieldBuilder setSpecialNotes(String specialNotes) {
            this.specialNotes = specialNotes;
            return this;
        }
        public void setEffectiveDate(Date effectiveDate) {
            this.effectiveDate = effectiveDate;
        }
        public void setLastUpdatedTime(Date lastUpdatedTime) {
            this.lastUpdatedTime = lastUpdatedTime;
        }

        public void setLastUpdateComments(String lastUpdateComments) {
            this.lastUpdateComments = lastUpdateComments;
        }

        public FieldBuilder setSupportedValues(List<SupportedValues> supportedValues) {
            this.supportedValues = supportedValues;
            return this;
        }

        public Field build() {
            return new Field(this);
        }
    }
}
