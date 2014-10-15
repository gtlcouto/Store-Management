package case2models;

import case2models.VendorModel;
import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-05T05:00:25")
@StaticMetamodel(ProductModel.class)
public class ProductModel_ { 

    public static volatile SingularAttribute<ProductModel, String> prodcd;
    public static volatile SingularAttribute<ProductModel, String> vensku;
    public static volatile SingularAttribute<ProductModel, Double> msrp;
    public static volatile SingularAttribute<ProductModel, Double> costprice;
    public static volatile SingularAttribute<ProductModel, Integer> qoo;
    public static volatile SingularAttribute<ProductModel, Integer> rop;
    public static volatile SingularAttribute<ProductModel, Serializable> qrcode;
    public static volatile SingularAttribute<ProductModel, VendorModel> vendorno;
    public static volatile SingularAttribute<ProductModel, Integer> eoq;
    public static volatile SingularAttribute<ProductModel, Integer> qoh;
    public static volatile SingularAttribute<ProductModel, String> prodnam;

}