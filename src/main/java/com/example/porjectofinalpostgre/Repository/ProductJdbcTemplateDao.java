package com.example.porjectofinalpostgre.Repository;



import com.example.porjectofinalpostgre.Entity.Product;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ProductJdbcTemplateDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*
    public List<Product> find(){
        List<Product> c = this.jdbcTemplate.query("SELECT * FROM PRODUCTS", new ProductRowMapper());
        return c;
    }
    */
    public List<Product> find(){
        List<Product> c = this.jdbcTemplate.query("SELECT * FROM PRODUCTS", new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product=new Product();
                product.setIdProduct(rs.getInt(1));
                product.setNombre(rs.getString(2));
                product.setOferta(rs.getFloat(3));

                return product;
            }
        });
        return c;
    }

}
