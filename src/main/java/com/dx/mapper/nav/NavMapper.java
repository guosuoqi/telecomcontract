package com.dx.mapper.nav;

import com.dx.model.nav.NavTree;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NavMapper {

    List<NavTree> navTreeNode(int id);
}
