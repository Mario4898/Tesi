package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.FormAdmissibileValue;
import io.swagger.model.FormAnswer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FormFieldValidationResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-18T12:39:28.338Z")

public class FormFieldValidationResponse   {
  @JsonProperty("admissibleValues")
  @Valid
  @JsonInclude(value = Include.NON_NULL)
  private List<FormAdmissibileValue> admissibleValues = null;

  @JsonProperty("answers")
  @Valid
  @JsonInclude(value = Include.NON_NULL)
  private List<FormAnswer> answers = null;

  @JsonProperty("command")
  @JsonInclude(value = Include.NON_NULL)
  private String command = null;

  @JsonProperty("extractedValue")
  @JsonInclude(value = Include.NON_NULL)
  private String extractedValue = null;

  @JsonProperty("isValid")
  private Boolean isValid = false;

  public FormFieldValidationResponse admissibleValues(List<FormAdmissibileValue> admissibleValues) {
    this.admissibleValues = admissibleValues;
    return this;
  }

  public FormFieldValidationResponse addAdmissibleValuesItem(FormAdmissibileValue admissibleValuesItem) {
    if (this.admissibleValues == null) {
      this.admissibleValues = new ArrayList<FormAdmissibileValue>();
    }
    this.admissibleValues.add(admissibleValuesItem);
    return this;
  }

  /**
   * Get admissibleValues
   * @return admissibleValues
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<FormAdmissibileValue> getAdmissibleValues() {
    return admissibleValues;
  }

  public void setAdmissibleValues(List<FormAdmissibileValue> admissibleValues) {
    this.admissibleValues = admissibleValues;
  }

  public FormFieldValidationResponse answers(List<FormAnswer> answers) {
    this.answers = answers;
    return this;
  }

  public FormFieldValidationResponse addAnswersItem(FormAnswer answersItem) {
    if (this.answers == null) {
      this.answers = new ArrayList<FormAnswer>();
    }
    this.answers.add(answersItem);
    return this;
  }

  /**
   * Get answers
   * @return answers
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<FormAnswer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<FormAnswer> answers) {
    this.answers = answers;
  }

  public FormFieldValidationResponse command(String command) {
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

  public FormFieldValidationResponse extractedValue(String extractedValue) {
    this.extractedValue = extractedValue;
    return this;
  }

  /**
   * Get extractedValue
   * @return extractedValue
  **/
  @ApiModelProperty(value = "")


  public String getExtractedValue() {
    return extractedValue;
  }

  public void setExtractedValue(String extractedValue) {
    this.extractedValue = extractedValue;
  }

  public FormFieldValidationResponse isValid(Boolean isValid) {
    this.isValid = isValid;
    return this;
  }

  /**
   * Return true if field value is valid
   * @return isValid
  **/
  @ApiModelProperty(value = "Return true if field value is valid")


  public Boolean isIsValid() {
    return isValid;
  }

  public void setIsValid(Boolean isValid) {
    this.isValid = isValid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormFieldValidationResponse formFieldValidationResponse = (FormFieldValidationResponse) o;
    return Objects.equals(this.admissibleValues, formFieldValidationResponse.admissibleValues) &&
        Objects.equals(this.answers, formFieldValidationResponse.answers) &&
        Objects.equals(this.command, formFieldValidationResponse.command) &&
        Objects.equals(this.extractedValue, formFieldValidationResponse.extractedValue) &&
        Objects.equals(this.isValid, formFieldValidationResponse.isValid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(admissibleValues, answers, command, extractedValue, isValid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormFieldValidationResponse {\n");
    
    sb.append("    admissibleValues: ").append(toIndentedString(admissibleValues)).append("\n");
    sb.append("    answers: ").append(toIndentedString(answers)).append("\n");
    sb.append("    command: ").append(toIndentedString(command)).append("\n");
    sb.append("    extractedValue: ").append(toIndentedString(extractedValue)).append("\n");
    sb.append("    isValid: ").append(toIndentedString(isValid)).append("\n");
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

