package com.xgh.model.command.supplier;

import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.supplier.commands.DeleteSupplier;
import com.xgh.model.command.supplier.commands.RegisterSupplier;
import com.xgh.model.command.supplier.commands.UpdateSupplier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suppliers")
public class SupplierCommandController extends BasicCommandController<RegisterSupplier, UpdateSupplier, DeleteSupplier> {
    @Override
    protected String getBasePath() {
        return "/suppliers";
    }
}
