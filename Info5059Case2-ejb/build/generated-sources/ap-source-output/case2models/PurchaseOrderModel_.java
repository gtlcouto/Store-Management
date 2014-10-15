package case2models;

import case2models.PurchaseOrderLineItemsModel;
import case2models.VendorModel;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-05T05:00:25")
@StaticMetamodel(PurchaseOrderModel.class)
public class PurchaseOrderModel_ { 

    public static volatile SingularAttribute<PurchaseOrderModel, BigDecimal> amount;
    public static volatile SingularAttribute<PurchaseOrderModel, Date> podate;
    public static volatile CollectionAttribute<PurchaseOrderModel, PurchaseOrderLineItemsModel> purchaseOrderLineItemsModelCollection;
    public static volatile SingularAttribute<PurchaseOrderModel, VendorModel> vendorno;
    public static volatile SingularAttribute<PurchaseOrderModel, Integer> ponumber;

}