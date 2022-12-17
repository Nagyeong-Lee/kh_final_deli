package kh.deli.domain.owner.mapper;

import kh.deli.global.entity.MenuDTO;
import kh.deli.global.entity.StoreDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OwnerMenuMapper {

    public void insertMenu(MenuDTO dto);
    List<String> menuInfo(int store_seq);

    List<MenuDTO> menuList(String menu_group,int store_seq);


}
