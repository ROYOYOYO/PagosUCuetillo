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
import org.openjpa.entidades.Alumno;
import org.openjpa.entidades.Carrera;
import org.openjpa.entidades.Pago;

public class PagoControl implements Serializable {

    public PagoControl(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int insertar(Pago pago) throws EntidadPreexistenteException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pago);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscaPago(pago.getPagoId()) != null) {
                throw new EntidadPreexistenteException("Pago " + pago + " ya existe en la base de datos.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return pago.getPagoId();
    }

    public int editar(Pago pago) throws NoExisteEntidadException {
        EntityManager em = null;
        int aux = 0;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pago = em.merge(pago);
            em.getTransaction().commit();
            aux = pago.getPagoId();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pago.getPagoId();
                if (buscaPago(id) == null) {
                    throw new NoExisteEntidadException("El pago con el id " + id + " no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return aux;
    }

    public void eliminar(Integer id) throws NoExisteEntidadException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago pago;
            try {
                pago = em.getReference(Pago.class, id);
                pago.getPagoId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("El pago con el id " + id + " no existe.", enfe);
            }
            em.remove(pago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pago> buscaPagos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Pago> query = em.createNamedQuery("Pago.seleccionaTodos", Pago.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Pago buscaPago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pago.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalPago() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pago> rt = cq.from(Pago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public int obtenerAlumnoId(String alumno) {
        EntityManager em = getEntityManager();
        TypedQuery<Alumno> query = em.createQuery("SELECT c FROM Alumno c WHERE c.nombre = :descripcion", Alumno.class);
        query.setParameter("descripcion", alumno);
        Alumno carrera = query.getSingleResult();
        return carrera.getAlumnoId();
    }
    
    public Alumno obtenerAlumnoPorId(int alumnoId) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, alumnoId);
        } finally {
            em.close();
        }
    }
    
    public List<Alumno> obtenerAlumnosOrdenadasPorId() {
    EntityManager em = getEntityManager();
    TypedQuery<Alumno> query = em.createQuery("SELECT a FROM Alumno a", Alumno.class);
    return query.getResultList();
    }
}
