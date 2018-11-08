package com.xgh.model.command.operational.animal;

import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.operational.animal.commands.DeleteAnimal;
import com.xgh.model.command.operational.animal.commands.RegisterAnimal;
import com.xgh.model.command.operational.animal.commands.UpdateAnimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animals")
public class AnimalCommandController extends BasicCommandController<RegisterAnimal, UpdateAnimal, DeleteAnimal> {

    @Override
    protected String getBasePath() {
        return "/animals";
    }
}
