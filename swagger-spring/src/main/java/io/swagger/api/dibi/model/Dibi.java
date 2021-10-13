
package io.swagger.api.dibi.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "longitude",
    "latitude",
    "pagesize",
    "resultsInPage",
    "resultsNumber",
    "results",
    "currentPage",
    "nextPage",
    "lastPage"
})
public class Dibi {

    @JsonProperty("status")
    private String status;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("pagesize")
    private Integer pagesize;
    @JsonProperty("resultsInPage")
    private Integer resultsInPage;
    @JsonProperty("resultsNumber")
    private Integer resultsNumber;
    @JsonProperty("results")
    private List<DibiResult> results = null;
    @JsonProperty("currentPage")
    private Integer currentPage;
    @JsonProperty("nextPage")
    private Integer nextPage;
    @JsonProperty("lastPage")
    private Integer lastPage;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("pagesize")
    public Integer getPagesize() {
        return pagesize;
    }

    @JsonProperty("pagesize")
    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    @JsonProperty("resultsInPage")
    public Integer getResultsInPage() {
        return resultsInPage;
    }

    @JsonProperty("resultsInPage")
    public void setResultsInPage(Integer resultsInPage) {
        this.resultsInPage = resultsInPage;
    }

    @JsonProperty("resultsNumber")
    public Integer getResultsNumber() {
        return resultsNumber;
    }

    @JsonProperty("resultsNumber")
    public void setResultsNumber(Integer resultsNumber) {
        this.resultsNumber = resultsNumber;
    }

    @JsonProperty("results")
    public List<DibiResult> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<DibiResult> results) {
        this.results = results;
    }

    @JsonProperty("currentPage")
    public Integer getCurrentPage() {
        return currentPage;
    }

    @JsonProperty("currentPage")
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @JsonProperty("nextPage")
    public Integer getNextPage() {
        return nextPage;
    }

    @JsonProperty("nextPage")
    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    @JsonProperty("lastPage")
    public Integer getLastPage() {
        return lastPage;
    }

    @JsonProperty("lastPage")
    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

}
