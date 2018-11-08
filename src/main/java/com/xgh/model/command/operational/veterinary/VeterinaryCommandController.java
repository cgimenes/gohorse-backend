package com.xgh.model.command.operational.veterinary;

import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.operational.veterinary.commands.DeleteVeterinary;
import com.xgh.model.command.operational.veterinary.commands.RegisterVeterinary;
import com.xgh.model.command.operational.veterinary.commands.UpdateVeterinary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veterinarians")
public class VeterinaryCommandController extends BasicCommandController<RegisterVeterinary, UpdateVeterinary, DeleteVeterinary> {
    @Override
    protected String getBasePath() {
        return "/veterinarians";
    }
}
