package case2models;

import case2models.PurchaseOrderModel;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-05T05:00:25")
@StaticMetamodel(PurchaseOrderLineItemsModel.class)
public class PurchaseOrderLineItemsModel_ { 

    public static volatile SingularAttribute<PurchaseOrderLineItemsModel, String> prodcd;
    public static volatile SingularAttribute<PurchaseOrderLineItemsModel, BigDecimal> price;
    public static volatile SingularAttribute<PurchaseOrderLineItemsModel, Integer> lineid;
    public static volatile SingularAttribute<PurchaseOrderLineItemsModel, Integer> qty;
    public static volatile SingularAttribute<PurchaseOrderLineItemsModel, PurchaseOrderModel> ponumber;

}