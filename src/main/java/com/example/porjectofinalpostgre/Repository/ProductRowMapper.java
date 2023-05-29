package com.example.porjectofinalpostgre.Repository;

import com.example.porjectofinalpostgre.Entity.Product;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.parameters.P;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet arg0, int arg1) throws SQLException{

        Product product=new Product( );
    return product;
    }
}
