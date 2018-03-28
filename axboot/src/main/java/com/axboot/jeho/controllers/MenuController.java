package com.axboot.jeho.controllers;

import com.axboot.jeho.domain.program.menu.Menu;
import com.axboot.jeho.domain.program.menu.MenuRequestVO;
import com.axboot.jeho.domain.program.menu.MenuService;
import com.axboot.jeho.domain.user.auth.menu.AuthGroupMenu;
import com.axboot.jeho.domain.user.auth.menu.AuthGroupMenuService;
import com.axboot.jeho.domain.user.auth.menu.AuthGroupMenuVO;
import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;


@Controller
@RequestMapping(value = "/api/v2/menu")
public class MenuController extends BaseController {

    @Inject
    private MenuService menuService;

    @Inject
    private AuthGroupMenuService authGroupMenuService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse menuList(RequestParams requestParams) {
        List<Menu> list = menuService.get(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody MenuRequestVO menuVO) {
        menuService.processMenu(menuVO);
        return ok();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse update(@PathVariable Long id, @RequestBody Menu menu) {
        menuService.updateMenu(id, menu);
        return ok();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET}, produces = APPLICATION_JSON)
    public Menu update(@PathVariable Long id) {
        return menuService.findOne(id);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public AuthGroupMenuVO authMapList(RequestParams requestParams) {
        return authGroupMenuService.get(requestParams);
    }

    @RequestMapping(value = "/auth", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<AuthGroupMenu> authGroupMenuList) {
        authGroupMenuService.saveAuth(authGroupMenuList);
        return ok();
    }
}
