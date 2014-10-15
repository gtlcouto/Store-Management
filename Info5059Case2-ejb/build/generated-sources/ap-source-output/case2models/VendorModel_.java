package case2models;

import case2models.ProductModel;
import case2models.PurchaseOrderModel;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-05T05:00:25")
@StaticMetamodel(VendorModel.class)
public class VendorModel_ { 

    public static volatile CollectionAttribute<VendorModel, ProductModel> productModelCollection;
    public static volatile SingularAttribute<VendorModel, String> postalcode;
    public static volatile SingularAttribute<VendorModel, String> phone;
    public static volatile SingularAttribute<VendorModel, String> email;
    public static volatile SingularAttribute<VendorModel, String> name;
    public static volatile SingularAttribute<VendorModel, String> province;
    public static volatile SingularAttribute<VendorModel, String> address1;
    public static volatile SingularAttribute<VendorModel, Integer> vendorno;
    public static volatile SingularAttribute<VendorModel, String> vendortype;
    public static volatile CollectionAttribute<VendorModel, PurchaseOrderModel> purchaseOrderModelCollection;
    public static volatile SingularAttribute<VendorModel, String> city;

}