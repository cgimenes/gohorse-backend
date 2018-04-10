package com.xgh.buildingblocks.valueobject;

import com.xgh.JsonSerializable;

// TODO corrigir o problema da falta de validação na deserialização
public interface ValueObject extends JsonSerializable {
	static final long serialVersionUID = 594399895910183430L;
}
