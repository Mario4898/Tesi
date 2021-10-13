package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FormAdmissibileValue
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-18T12:39:28.338Z")

public class FormAdmissibileValue   {
  @JsonProperty("alias")
  @JsonInclude(value = Include.NON_NULL)
  private String alias = null;

  @JsonProperty("description")
  @JsonInclude(value = Include.NON_NULL)
  private String description = null;

  @JsonProperty("media")
  @JsonInclude(value = Include.NON_NULL)
  private String media = null;

  @JsonProperty("mediaType")
  @JsonInclude(value = Include.NON_NULL)
  private String mediaType = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("price")
  @JsonInclude(value = Include.NON_NULL)
  private String price = null;

  @JsonProperty("quantity")
  @JsonInclude(value = Include.NON_NULL)
  private Integer quantity = null;

  public FormAdmissibileValue alias(String alias) {
    this.alias = alias;
    return this;
  }

  /**
   * Get alias
   * @return alias
  **/
  @ApiModelProperty(value = "")


  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public FormAdmissibileValue description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public FormAdmissibileValue media(String media) {
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

  public FormAdmissibileValue mediaType(String mediaType) {
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

  public FormAdmissibileValue name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FormAdmissibileValue price(String price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  **/
  @ApiModelProperty(value = "")


  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public FormAdmissibileValue quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
  **/
  @ApiModelProperty(value = "")


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormAdmissibileValue formAdmissibileValue = (FormAdmissibileValue) o;
    return Objects.equals(this.alias, formAdmissibileValue.alias) &&
        Objects.equals(this.description, formAdmissibileValue.description) &&
        Objects.equals(this.media, formAdmissibileValue.media) &&
        Objects.equals(this.mediaType, formAdmissibileValue.mediaType) &&
        Objects.equals(this.name, formAdmissibileValue.name) &&
        Objects.equals(this.price, formAdmissibileValue.price) &&
        Objects.equals(this.quantity, formAdmissibileValue.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(alias, description, media, mediaType, name, price, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormAdmissibileValue {\n");
    
    sb.append("    alias: ").append(toIndentedString(alias)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    media: ").append(toIndentedString(media)).append("\n");
    sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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

