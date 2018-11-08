package com.xgh.model.command.operational.product;

import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.operational.product.commands.DeleteProduct;
import com.xgh.model.command.operational.product.commands.RegisterProduct;
import com.xgh.model.command.operational.product.commands.UpdateProduct;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductCommandController extends BasicCommandController<RegisterProduct, UpdateProduct, DeleteProduct> {
    @Override
    protected String getBasePath() {
        return "/products";
    }
}
