package br.com.mrcruz.controller;


import java.util.UUID;

import br.com.mrcruz.domain.User;
import br.com.mrcruz.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;


@Path("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("size") @DefaultValue("2") Integer size) {
        var users = this.userService.findAll(page, size);
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID id) {
        var user = this.userService.findById(id);
        return Response.ok(user).build();
    }


    @POST
    @Transactional
    public Response create(User user) {
        user = this.userService.create(user);
        return Response.status(Response.Status.CREATED)
                .entity(user)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") UUID id, User userUpdate) {
        var user = this.userService.update(id, userUpdate);
        return Response.ok(user).build();
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") UUID id) {
        this.userService.deleteById(id);
        return Response.noContent().build();
    }
}
