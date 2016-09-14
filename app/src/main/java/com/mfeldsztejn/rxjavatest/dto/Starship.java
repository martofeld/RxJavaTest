package com.mfeldsztejn.rxjavatest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StarShip extends Item {

    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("cost_in_credits")
    @Expose
    private String costInCredits;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("max_atmosphering_speed")
    @Expose
    private String maxAtmospheringSpeed;
    @SerializedName("crew")
    @Expose
    private String crew;
    @SerializedName("passengers")
    @Expose
    private String passengers;
    @SerializedName("cargo_capacity")
    @Expose
    private String cargoCapacity;
    @SerializedName("consumables")
    @Expose
    private String consumables;
    @SerializedName("hyperdrive_rating")
    @Expose
    private String hyperdriveRating;
    @SerializedName("MGLT")
    @Expose
    private String mGLT;
    @SerializedName("starship_class")
    @Expose
    private String starshipClass;
    @SerializedName("pilots")
    @Expose
    private List<Object> pilots = new ArrayList<Object>();
    @SerializedName("films")
    @Expose
    private List<String> films = new ArrayList<String>();
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("edited")
    @Expose
    private String edited;
    @SerializedName("url")
    @Expose
    private String url;


    /**
     * @return The model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return The manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer The manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return The costInCredits
     */
    public String getCostInCredits() {
        return costInCredits;
    }

    /**
     * @param costInCredits The cost_in_credits
     */
    public void setCostInCredits(String costInCredits) {
        this.costInCredits = costInCredits;
    }

    /**
     * @return The length
     */
    public String getLength() {
        return length;
    }

    /**
     * @param length The length
     */
    public void setLength(String length) {
        this.length = length;
    }

    /**
     * @return The maxAtmospheringSpeed
     */
    public String getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
    }

    /**
     * @param maxAtmospheringSpeed The max_atmosphering_speed
     */
    public void setMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
    }

    /**
     * @return The crew
     */
    public String getCrew() {
        return crew;
    }

    /**
     * @param crew The crew
     */
    public void setCrew(String crew) {
        this.crew = crew;
    }

    /**
     * @return The passengers
     */
    public String getPassengers() {
        return passengers;
    }

    /**
     * @param passengers The passengers
     */
    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    /**
     * @return The cargoCapacity
     */
    public String getCargoCapacity() {
        return cargoCapacity;
    }

    /**
     * @param cargoCapacity The cargo_capacity
     */
    public void setCargoCapacity(String cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    /**
     * @return The consumables
     */
    public String getConsumables() {
        return consumables;
    }

    /**
     * @param consumables The consumables
     */
    public void setConsumables(String consumables) {
        this.consumables = consumables;
    }

    /**
     * @return The hyperdriveRating
     */
    public String getHyperdriveRating() {
        return hyperdriveRating;
    }

    /**
     * @param hyperdriveRating The hyperdrive_rating
     */
    public void setHyperdriveRating(String hyperdriveRating) {
        this.hyperdriveRating = hyperdriveRating;
    }

    /**
     * @return The mGLT
     */
    public String getMGLT() {
        return mGLT;
    }

    /**
     * @param mGLT The MGLT
     */
    public void setMGLT(String mGLT) {
        this.mGLT = mGLT;
    }

    /**
     * @return The starshipClass
     */
    public String getStarshipClass() {
        return starshipClass;
    }

    /**
     * @param starshipClass The starship_class
     */
    public void setStarshipClass(String starshipClass) {
        this.starshipClass = starshipClass;
    }

    /**
     * @return The pilots
     */
    public List<Object> getPilots() {
        return pilots;
    }

    /**
     * @param pilots The pilots
     */
    public void setPilots(List<Object> pilots) {
        this.pilots = pilots;
    }

    /**
     * @return The films
     */
    public List<String> getFilms() {
        return films;
    }

    /**
     * @param films The films
     */
    public void setFilms(List<String> films) {
        this.films = films;
    }

    /**
     * @return The created
     */
    public String getCreated() {
        return created;
    }

    /**
     * @param created The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * @return The edited
     */
    public String getEdited() {
        return edited;
    }

    /**
     * @param edited The edited
     */
    public void setEdited(String edited) {
        this.edited = edited;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
