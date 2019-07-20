package com.dx.service.nav;

import com.dx.mapper.nav.NavMapper;
import com.dx.model.nav.NavTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavServiceImpl implements NavService {
    @Autowired
    private NavMapper navMapper;


    @Override
    public List<NavTree> toShowTree() {
        int id = 0;
        List<NavTree> navTreeNode = navTreeNode(id);
        return navTreeNode;
    }
    private List<NavTree> navTreeNode(int id) {
        List<NavTree> navTreeNode = navMapper.navTreeNode(id);
        for (NavTree navTree : navTreeNode) {
            Integer id2 = navTree.getId();
            List<NavTree> navTreeNodeNew = navTreeNode(id2);
            if (navTreeNodeNew == null || navTreeNodeNew.size() <= 0) {
                navTree.setSelectable(true);
            } else {
                navTree.setSelectable(false);
                navTree.setNodes(navTreeNodeNew);
            }
        }
        return navTreeNode;
    }
}