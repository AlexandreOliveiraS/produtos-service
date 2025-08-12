package com.alexandre.produtos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
    // listAll(), findById(), persist(), deleteById() etc. já vêm prontos
}
