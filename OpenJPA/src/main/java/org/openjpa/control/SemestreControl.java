package org.openjpa.control;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.openjpa.control.exceptions.EntidadPreexistenteException;
import org.openjpa.control.exceptions.NoExisteEntidadException;
import org.openjpa.entidades.Semestre;

public class SemestreControl implements Serializable {

    public SemestreControl(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int insertar(Semestre semestre) throws EntidadPreexistenteException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(semestre);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscaSemestre(semestre.getSemestreId()) != null) {
                throw new EntidadPreexistenteException("Semestre " + semestre + " ya existe en la base de datos.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return semestre.getSemestreId();
    }

    public void editar(Semestre semestre) throws NoExisteEntidadException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            semestre = em.merge(semestre);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = semestre.getSemestreId();
                if (buscaSemestre(id) == null) {
                    throw new NoExisteEntidadException("El semestre con el id " + id + " no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminar(Integer id) throws NoExisteEntidadException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Semestre semestre;
            try {
                semestre = em.getReference(Semestre.class, id);
                semestre.getSemestreId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("El semestre con el id " + id + " no existe.", enfe);
            }
            em.remove(semestre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Semestre> buscaSemestres() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Semestre> query = em.createNamedQuery("Semestre.seleccionaTodos", Semestre.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Semestre buscaSemestre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Semestre.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalSemestre() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Semestre> rt = cq.from(Semestre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
