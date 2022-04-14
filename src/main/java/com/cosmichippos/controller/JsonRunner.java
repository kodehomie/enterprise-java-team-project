package com.cosmichippos.controller;
import com.cosmichippos.entity.Genre;
import com.cosmichippos.persistence.GenreDao;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.json.JSONException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The type Json runner.
 */
@Path("/json")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class JsonRunner extends ObjectMapper {

    /**
     * The Dao.
     */
    GenreDao dao;

    /**
     * Genre service response.
     *
     * @param g the g
     * @return the response
     * @throws JSONException the json exception
     */
    @GET
    @Path("{g}")
    @Produces("application/json")
    public Response genreService(@PathParam("g") String g) throws JSONException {

        dao = new GenreDao();
        JSONObject jsonObject = new JSONObject();

        Genre inputGenre;
        long inputId;
        String inputName;
        long inputParent;
        String parentName;

        inputGenre = dao.getByName(g);

        if (inputGenre != null) {

            inputId = inputGenre.getId();
            inputName = inputGenre.getName();

            inputParent = inputGenre.getParentId() != null
                    ? inputGenre.getParentId() : 0;
            parentName = inputParent != 0
                    ? dao.getById(inputParent).getName() : "None";

        } else {

            inputId = 0;
            inputName = String.format("No genre found for '%s'...", g);
            inputParent = 0;
            parentName = "None";

        }

        jsonObject.put("genreId", inputId);
        jsonObject.put("genreName", inputName);
        jsonObject.put("genreParentId", inputParent);
        jsonObject.put("genreParentName", parentName);

        return Response.status(200).entity(jsonObject.toString()).build();
    }

}