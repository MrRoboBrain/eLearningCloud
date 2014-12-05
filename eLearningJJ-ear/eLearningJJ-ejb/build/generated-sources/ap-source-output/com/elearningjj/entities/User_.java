package com.elearningjj.entities;

import com.elearningjj.entities.Subject;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-02T16:45:46")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile CollectionAttribute<User, Subject> subjectCollection;
    public static volatile SingularAttribute<User, String> userRoles;
    public static volatile SingularAttribute<User, String> userPassword;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, Integer> userId;

}