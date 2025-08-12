package com.alexandre.produtos;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoRepository repo;

    @GET
    public List<Produto> listar() {
        return repo.listAll();
    }

    @GET
    @Path("/{id}")
    public Produto buscar(@PathParam("id") Long id) {
        Produto p = repo.findById(id);
        if (p == null) throw new NotFoundException("Produto não encontrado");
        return p;
    }

    @POST
    @Transactional
    public Response criar(Produto novo) {
        repo.persist(novo);
        return Response.status(Response.Status.CREATED).entity(novo).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Produto atualizar(@PathParam("id") Long id, Produto dados) {
        Produto p = repo.findById(id);
        if (p == null) throw new NotFoundException("Produto não encontrado");
        p.nome = dados.nome;
        p.preco = dados.preco;
        p.estoque = dados.estoque;
        return p;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        boolean ok = repo.deleteById(id);
        if (!ok) throw new NotFoundException("Produto não encontrado");
    }
}
