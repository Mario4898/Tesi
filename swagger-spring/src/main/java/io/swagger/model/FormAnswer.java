package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FormAnswer
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-18T12:39:28.338Z")

public class FormAnswer   {
  @JsonProperty("answerText")
  private String answerText = null;

  @JsonProperty("media")
  @JsonInclude(value = Include.NON_NULL)
  private String media = null;

  @JsonProperty("mediaType")
  @JsonInclude(value = Include.NON_NULL)
  private String mediaType = null;

  @JsonProperty("rawData")
  @JsonInclude(value = Include.NON_NULL)
  private Object rawData = null;

  public FormAnswer answerText(String answerText) {
    this.answerText = answerText;
    return this;
  }

  /**
   * Get answerText
   * @return answerText
  **/
  @ApiModelProperty(value = "")


  public String getAnswerText() {
    return answerText;
  }

  public void setAnswerText(String answerText) {
    this.answerText = answerText;
  }

  public FormAnswer media(String media) {
    this.media = media;
    return this;
  }

  /**
   * Get media
   * @return media
  **/
  @ApiModelProperty(value = "")


  public String getMedia() {
    return media;
  }

  public void setMedia(String media) {
    this.media = media;
  }

  public FormAnswer mediaType(String mediaType) {
    this.mediaType = mediaType;
    return this;
  }

  /**
   * Get mediaType
   * @return mediaType
  **/
  @ApiModelProperty(value = "")


  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  public FormAnswer rawData(Object rawData) {
    this.rawData = rawData;
    return this;
  }

  /**
   * Get rawData
   * @return rawData
  **/
  @ApiModelProperty(value = "")


  public Object getRawData() {
    return rawData;
  }

  public void setRawData(Object rawData) {
    this.rawData = rawData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormAnswer formAnswer = (FormAnswer) o;
    return Objects.equals(this.answerText, formAnswer.answerText) &&
        Objects.equals(this.media, formAnswer.media) &&
        Objects.equals(this.mediaType, formAnswer.mediaType) &&
        Objects.equals(this.rawData, formAnswer.rawData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(answerText, media, mediaType, rawData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormAnswer {\n");
    
    sb.append("    answerText: ").append(toIndentedString(answerText)).append("\n");
    sb.append("    media: ").append(toIndentedString(media)).append("\n");
    sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
    sb.append("    rawData: ").append(toIndentedString(rawData)).append("\n");
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

