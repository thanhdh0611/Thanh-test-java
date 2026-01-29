package book.store.service;


import book.store.payload.response.RoleRsp;

import java.util.Set;

public interface IRoleService {

    Set<RoleRsp> getAllRole();
}
