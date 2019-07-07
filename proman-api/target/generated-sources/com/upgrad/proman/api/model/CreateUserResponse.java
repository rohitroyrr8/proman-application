package com.upgrad.proman.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.upgrad.proman.api.model.UserStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateUserResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-07-07T13:57:58.487+05:30")

public class CreateUserResponse   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("status")
  private UserStatusType status = null;

  public CreateUserResponse id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier in a standard UUID format generated by API backend
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Unique identifier in a standard UUID format generated by API backend")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CreateUserResponse status(UserStatusType status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public UserStatusType getStatus() {
    return status;
  }

  public void setStatus(UserStatusType status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateUserResponse createUserResponse = (CreateUserResponse) o;
    return Objects.equals(this.id, createUserResponse.id) &&
        Objects.equals(this.status, createUserResponse.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateUserResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

