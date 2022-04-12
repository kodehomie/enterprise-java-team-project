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

@Path("/json")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class JsonRunner extends ObjectMapper {

    GenreDao dao;

    @GET
    @Path("{g}")
    @Produces("application/json")
    public Response genreService(@PathParam("g") String g) throws JSONException {
        dao = new GenreDao();
        JSONObject jsonObject = new JSONObject();
        Genre inputGenre = dao.getByName(g);
        long inputId = inputGenre.getId();
        String inputName = inputGenre.getName();
        long inputParent = inputGenre.getParentId();
        Genre parentIdr = dao.getById(inputParent);
        String parentName = parentIdr.getName();
        jsonObject.put("genreId", inputId);
        jsonObject.put("genreName", inputName);
        jsonObject.put("genreParentId", inputParent);
        jsonObject.put("genreParentName", parentName);

        String result = "@Produces(\"application/json\") Output: \n\nGenre Checker Output: \n\n" + jsonObject;
        return Response.status(200).entity(result).build();
    }

}