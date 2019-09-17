package clinic.com.example.clinic.infrastructure.repository;

import clinic.com.example.clinic.infrastructure.entity.Doctor;
import clinic.com.example.clinic.infrastructure.entity.Visit;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VisitRepositoryImpl implements CustomVisitRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Visit> findByCriteria(Long doctorId, String specialization) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Visit> query = cb.createQuery(Visit.class);
        //root to from w zapytaniu SQL
        Root root = query.from(Visit.class);
        //tutaj bedziemy umieszczali kolejne joiny
        Metamodel m = entityManager.getMetamodel();
        EntityType<Visit> visitMetaModel = m.entity(Visit.class);
        Join<Visit, Doctor> doctor = root.join(visitMetaModel.getSingularAttribute("doctor"));
        //where
        List<Predicate> predicates = new ArrayList<>();
        if (doctorId != 0) {
            predicates.add(cb.equal(doctor.get("id"), doctorId));
        }
        if (specialization != null) {
            predicates.add(cb.equal(doctor.get("specialization"), specialization));
        }

        CriteriaQuery select = query.select(root);
        //sortowanie
        Order order = new OrderImpl(doctor.get("firstName"));
        //wywolanie zapytania
        TypedQuery typedQuery = entityManager.createQuery(
                select.where(cb.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(order));
        return typedQuery.getResultList();
    }
}
