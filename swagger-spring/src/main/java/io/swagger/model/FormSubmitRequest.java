package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.FormField;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FormSubmitRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-18T12:39:28.338Z")

public class FormSubmitRequest   {
  @JsonProperty("botId")
  private String botId = null;

  @JsonProperty("currentUrl")
  private String currentUrl = null;

  @JsonProperty("fields")
  @Valid
  private List<FormField> fields = null;

  @JsonProperty("formName")
  private String formName = null;

  @JsonProperty("lang")
  private String lang = null;

  @JsonProperty("userId")
  private String userId = null;

  public FormSubmitRequest botId(String botId) {
    this.botId = botId;
    return this;
  }

  /**
   * Get botId
   * @return botId
  **/
  @ApiModelProperty(value = "")


  public String getBotId() {
    return botId;
  }

  public void setBotId(String botId) {
    this.botId = botId;
  }

  public FormSubmitRequest currentUrl(String currentUrl) {
    this.currentUrl = currentUrl;
    return this;
  }

  /**
   * Get currentUrl
   * @return currentUrl
  **/
  @ApiModelProperty(value = "")


  public String getCurrentUrl() {
    return currentUrl;
  }

  public void setCurrentUrl(String currentUrl) {
    this.currentUrl = currentUrl;
  }

  public FormSubmitRequest fields(List<FormField> fields) {
    this.fields = fields;
    return this;
  }

  public FormSubmitRequest addFieldsItem(FormField fieldsItem) {
    if (this.fields == null) {
      this.fields = new ArrayList<FormField>();
    }
    this.fields.add(fieldsItem);
    return this;
  }

  /**
   * Get fields
   * @return fields
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<FormField> getFields() {
    return fields;
  }

  public void setFields(List<FormField> fields) {
    this.fields = fields;
  }

  public FormSubmitRequest formName(String formName) {
    this.formName = formName;
    return this;
  }

  /**
   * Get formName
   * @return formName
  **/
  @ApiModelProperty(value = "")


  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public FormSubmitRequest lang(String lang) {
    this.lang = lang;
    return this;
  }

  /**
   * Get lang
   * @return lang
  **/
  @ApiModelProperty(value = "")


  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public FormSubmitRequest userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormSubmitRequest formSubmitRequest = (FormSubmitRequest) o;
    return Objects.equals(this.botId, formSubmitRequest.botId) &&
        Objects.equals(this.currentUrl, formSubmitRequest.currentUrl) &&
        Objects.equals(this.fields, formSubmitRequest.fields) &&
        Objects.equals(this.formName, formSubmitRequest.formName) &&
        Objects.equals(this.lang, formSubmitRequest.lang) &&
        Objects.equals(this.userId, formSubmitRequest.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(botId, currentUrl, fields, formName, lang, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormSubmitRequest {\n");
    
    sb.append("    botId: ").append(toIndentedString(botId)).append("\n");
    sb.append("    currentUrl: ").append(toIndentedString(currentUrl)).append("\n");
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
    sb.append("    formName: ").append(toIndentedString(formName)).append("\n");
    sb.append("    lang: ").append(toIndentedString(lang)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

