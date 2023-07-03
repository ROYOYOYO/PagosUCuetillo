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
import org.openjpa.entidades.Carrera;

public class CarreraControl implements Serializable {

    public CarreraControl(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int insertar(Carrera carrera) throws EntidadPreexistenteException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(carrera);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscaCarrera(carrera.getCarreraId()) != null) {
                throw new EntidadPreexistenteException("Carrera " + carrera + " ya existe en la base de datos.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return carrera.getCarreraId();
    }

    public void editar(Carrera carrera) throws NoExisteEntidadException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            carrera = em.merge(carrera);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carrera.getCarreraId();
                if (buscaCarrera(id) == null) {
                    throw new NoExisteEntidadException("La carrera con el id " + id + " no existe.");
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
            Carrera carrera;
            try {
                carrera = em.getReference(Carrera.class, id);
                carrera.getCarreraId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("La carrera con el id " + id + " no existe.", enfe);
            }
            em.remove(carrera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carrera> buscaCarreras() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Carrera> query = em.createNamedQuery("Carrera.seleccionaTodos", Carrera.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Carrera buscaCarrera(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrera.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalCarrera() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carrera> rt = cq.from(Carrera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
