package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.FormAdmissibileValue;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FormFieldValuesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-18T12:39:28.338Z")

public class FormFieldValuesResponse   {
  @JsonProperty("command")
  @JsonInclude(value = Include.NON_NULL)
  private String command = null;

  @JsonProperty("question")
  @JsonInclude(value = Include.NON_NULL)
  private String question = null;

  @JsonProperty("values")
  @Valid
  private List<FormAdmissibileValue> values = null;

  public FormFieldValuesResponse command(String command) {
    this.command = command;
    return this;
  }

  /**
   * Get command
   * @return command
  **/
  @ApiModelProperty(value = "")


  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public FormFieldValuesResponse question(String question) {
    this.question = question;
    return this;
  }

  /**
   * Get question
   * @return question
  **/
  @ApiModelProperty(value = "")


  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public FormFieldValuesResponse values(List<FormAdmissibileValue> values) {
    this.values = values;
    return this;
  }

  public FormFieldValuesResponse addValuesItem(FormAdmissibileValue valuesItem) {
    if (this.values == null) {
      this.values = new ArrayList<FormAdmissibileValue>();
    }
    this.values.add(valuesItem);
    return this;
  }

  /**
   * Get values
   * @return values
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<FormAdmissibileValue> getValues() {
    return values;
  }

  public void setValues(List<FormAdmissibileValue> values) {
    this.values = values;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormFieldValuesResponse formFieldValuesResponse = (FormFieldValuesResponse) o;
    return Objects.equals(this.command, formFieldValuesResponse.command) &&
        Objects.equals(this.question, formFieldValuesResponse.question) &&
        Objects.equals(this.values, formFieldValuesResponse.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(command, question, values);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormFieldValuesResponse {\n");
    
    sb.append("    command: ").append(toIndentedString(command)).append("\n");
    sb.append("    question: ").append(toIndentedString(question)).append("\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
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

