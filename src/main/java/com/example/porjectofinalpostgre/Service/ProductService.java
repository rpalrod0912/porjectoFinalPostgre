package com.example.porjectofinalpostgre.Service;


import com.example.porjectofinalpostgre.Entity.Comment;
import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Entity.OrderItem;
import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Repository.OrderItemRepository;
import com.example.porjectofinalpostgre.Repository.OrderRepository;

import com.example.porjectofinalpostgre.Repository.ProductJdbcTemplateDao;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.dto.CommentDTO;
import com.example.porjectofinalpostgre.dto.ProductDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Transactional
@Service
public class ProductService {

    @Autowired
    private ProductJdbcTemplateDao productJdbc;
    @Autowired
    private  CommentService commentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Product addProduct(Product product){


        return productRepository.save(product);
    }
    public List<Product> getAllProducts(){

        return productJdbc.find();

    }

    public Product getProductById(Integer productId){
        System.out.println(productId);
        return productRepository.findById(productId).get();
    }

    public  List<Product> getProductByMarca(String marca){
        return productRepository.findByMarca(marca);
    }

    public List<Product> getProductByPrecio(Double precio){
        return productRepository.findByPrecio(precio);
    }

    public  ProductDTO getProductByNombre(String nombre){

        Product producto= productRepository.findByNombre(nombre);
        ProductDTO productoDTO =new ProductDTO();
        productoDTO.setIdProduct(producto.getIdProduct());
        productoDTO.setImagen(producto.getImagen());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setColor(producto.getColor());
        productoDTO.setMarca(producto.getMarca());
        productoDTO.setSexo(producto.getSexo());
        productoDTO.setOferta(producto.getOferta());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setCategoria(producto.getCategoria());
        productoDTO.setTalla(producto.getTalla());
        List<CommentDTO> comentariosProd = commentService.showCommentsByProduct(producto);
        productoDTO.setComentarios(comentariosProd);

        return productoDTO;
    }

    public List<Product> getSales(){
        List<Product> allProducts=getAllProducts();
        List <Product> salesArr= new ArrayList<>();
        for (Product product : allProducts){
            if (product.getOferta()!=null){
                salesArr.add(product);
            }
        }
        return salesArr;
    }
    public List<Product> getProductsPage(String numPagina){
        List<Product> products=this.getAllProducts();
        List<String> numPaginas=this.getAllPages();
        List<Product> productsPage=new ArrayList<Product>();
        Integer cont=0;
        for(String page : numPaginas){
            if(page.equals(numPagina)){

                   while (productsPage.size()<=10 && products.size()!=cont){
                       productsPage.add(products.get(cont));
                       cont++;


                   }
            }
            //Suponiendo que se quieren mostrar 10 productos
            cont+=11;
        }
        return productsPage;
    }
    public List<Product> getSaleProductsPaged(String numPagina){
        List<Product> products=this.getSales();
        List<String> numPaginas=this.getAllPages();
        List<Product> productsPage=new ArrayList<Product>();
        Integer cont=0;
        for(String page : numPaginas){
            if(page.equals(numPagina)){

                while (productsPage.size()<=10 && products.size()!=cont){
                    productsPage.add(products.get(cont));
                    cont++;


                }
            }
            //Suponiendo que se quieren mostrar 10 productos
            cont+=11;
        }
        return productsPage;
    }
    public List<String> getSalePages(){
        List <Product> products=this.getSales();
        List<String> listaPaginas=new ArrayList<String>();
        Integer cont=0;
        Integer numPag=1;
        Integer contadorProducto=0;
        for(Product product : products){
            //Suponiendo que se quieren mostrar 10 productos
            if(cont>=10){
                listaPaginas.add(numPag.toString());
                cont=0;
                numPag++;
            }
            cont++;
            contadorProducto++;
            if(products.size()-contadorProducto==0){
                listaPaginas.add(numPag.toString());
                cont=0;
                numPag++;
            }
        }
        return listaPaginas;
    }

    public List<String> getAllPages(){
        List<Product> products=this.getAllProducts();
        List<String> listaPaginas=new ArrayList<String>();
        Integer cont=0;
        Integer numPag=1;
        Integer contadorProducto=0;
        for(Product product : products){
            //Suponiendo que se quieren mostrar 10 productos
            if(cont>=10){
                listaPaginas.add(numPag.toString());
                cont=0;
                numPag++;
            }
            cont++;
            contadorProducto++;
            if(products.size()-contadorProducto==0){
                listaPaginas.add(numPag.toString());
                cont=0;
                numPag++;
            }
        }
        return listaPaginas;
    }

    public Product updateProduct(Integer productID, Product productRequest){

        //obtener docs de la DB
        //Dar valor de lar equisit al existete documento/entidad/objeto
        Product existingProduct=productRepository.findByIdProduct(productID);
        if(productRequest.getDescripcion()!=null){
            existingProduct.setDescripcion(productRequest.getDescripcion());
        }
        existingProduct.setOferta(productRequest.getOferta());
        if(productRequest.getNombre()!=null) {
            existingProduct.setNombre(productRequest.getNombre());
        }
        if(productRequest.getCategoria()!=null){
            existingProduct.setCategoria(productRequest.getCategoria());
        }
        if(productRequest.getPrecio()!=null){
            existingProduct.setPrecio(productRequest.getPrecio());
        }
        if(productRequest.getColor()!=null){
            existingProduct.setColor(productRequest.getColor());
        }
        if(productRequest.getSexo()!=null){
            existingProduct.setSexo(productRequest.getSexo());
        }
        if(productRequest.getMarca()!=null){
            existingProduct.setMarca(productRequest.getMarca());
        }
        if(productRequest.getTalla()!=null){
            existingProduct.setTalla(productRequest.getTalla());
        }
        if(productRequest.getTipo()!=null){
            existingProduct.setTipo(productRequest.getTipo());

        }
        if(productRequest.getUtilidad()!=null){
            existingProduct.setUtilidad(productRequest.getUtilidad());
        }

        if(productRequest.getImagen()!=null){
            existingProduct.setImagen(productRequest.getImagen());
        }

        return productRepository.save(existingProduct);
    }

    public String deleteProduct(Integer productId){
        List<OrderItem> productIdOrders=orderItemRepository.findByIdProduct(productId);
        for(OrderItem productIdOrder  : productIdOrders){
            orderService.deleteOrder(productIdOrder.getOrderId().toString());
        }
        productRepository.deleteById(productId);
        return productId+" producto eliminado de API ";
    }
}
