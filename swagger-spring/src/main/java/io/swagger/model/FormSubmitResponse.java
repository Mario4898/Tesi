package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.FormAnswer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FormSubmitResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-18T12:39:28.338Z")

public class FormSubmitResponse   {
  @JsonProperty("answers")
  @Valid
  private List<FormAnswer> answers = null;

  @JsonProperty("nextUrl")
  @JsonInclude(value = Include.NON_NULL)
  private String nextUrl = null;

  @JsonProperty("nextUrlLabel")
  @JsonInclude(value = Include.NON_NULL)
  private String nextUrlLabel = null;

  public FormSubmitResponse answers(List<FormAnswer> answers) {
    this.answers = answers;
    return this;
  }

  public FormSubmitResponse addAnswersItem(FormAnswer answersItem) {
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

  public FormSubmitResponse nextUrl(String nextUrl) {
    this.nextUrl = nextUrl;
    return this;
  }

  /**
   * Get nextUrl
   * @return nextUrl
  **/
  @ApiModelProperty(value = "")


  public String getNextUrl() {
    return nextUrl;
  }

  public void setNextUrl(String nextUrl) {
    this.nextUrl = nextUrl;
  }

  public FormSubmitResponse nextUrlLabel(String nextUrlLabel) {
    this.nextUrlLabel = nextUrlLabel;
    return this;
  }

  /**
   * Get nextUrlLabel
   * @return nextUrlLabel
  **/
  @ApiModelProperty(value = "")


  public String getNextUrlLabel() {
    return nextUrlLabel;
  }

  public void setNextUrlLabel(String nextUrlLabel) {
    this.nextUrlLabel = nextUrlLabel;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormSubmitResponse formSubmitResponse = (FormSubmitResponse) o;
    return Objects.equals(this.answers, formSubmitResponse.answers) &&
        Objects.equals(this.nextUrl, formSubmitResponse.nextUrl) &&
        Objects.equals(this.nextUrlLabel, formSubmitResponse.nextUrlLabel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(answers, nextUrl, nextUrlLabel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormSubmitResponse {\n");
    
    sb.append("    answers: ").append(toIndentedString(answers)).append("\n");
    sb.append("    nextUrl: ").append(toIndentedString(nextUrl)).append("\n");
    sb.append("    nextUrlLabel: ").append(toIndentedString(nextUrlLabel)).append("\n");
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

