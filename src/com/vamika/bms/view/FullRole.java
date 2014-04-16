package com.vamika.bms.view;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.vamika.bms.annotation.validation.Unique;
import com.vamika.bms.annotation.validation.UniqueList;
import com.vamika.bms.model.Role;
import com.vamika.bms.model.enums.EnableDisableStatus;
import com.vamika.bms.validator.Create;
import com.vamika.bms.validator.Update;

@Component
@UniqueList({
	@Unique(entity=Role.class, uniqueFields={"name"}, message = "{Unique.role.name}", groups = { Create.class, Update.class }),
})
public class FullRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(groups = { Update.class })
	private Integer id;

	@Size(min = 5, max = 250, groups = { Update.class, Create.class })
	private String name;
	
	@NotNull(groups = { Update.class, Create.class })
	private EnableDisableStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EnableDisableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableDisableStatus status) {
		this.status = status;
	}
}
