package com.xgh.model.command.veterinary;

import com.xgh.infra.controller.BasicCommandController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.model.command.veterinary.commands.DeleteVeterinary;
import com.xgh.model.command.veterinary.commands.RegisterVeterinary;
import com.xgh.model.command.veterinary.commands.UpdateVeterinary;

@RestController
@RequestMapping("/veterinarians")
public class VeterinaryCommandController extends BasicCommandController<RegisterVeterinary, UpdateVeterinary, DeleteVeterinary> {
    @Override
    protected String getBasePath() {
        return "/veterinarians";
    }
}
