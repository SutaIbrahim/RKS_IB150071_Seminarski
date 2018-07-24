using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using RKS_IB150071_WebServisi.Models;

namespace RKS_IB150071_WebServisi.Controllers
{
    public class GradoviController : ApiController
    {
        private RKS_150071Entities db = new RKS_150071Entities();

        // GET: api/Gradovi
        public IQueryable<Gradovi> GetGradovi()
        {
            return db.Gradovi;
        }

        // GET: api/Gradovi/5
        [ResponseType(typeof(Gradovi))]
        public IHttpActionResult GetGradovi(int id)
        {
            Gradovi gradovi = db.Gradovi.Find(id);
            if (gradovi == null)
            {
                return NotFound();
            }

            return Ok(gradovi);
        }

        // PUT: api/Gradovi/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutGradovi(int id, Gradovi gradovi)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != gradovi.GradID)
            {
                return BadRequest();
            }

            db.Entry(gradovi).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!GradoviExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Gradovi
        [ResponseType(typeof(Gradovi))]
        public IHttpActionResult PostGradovi(Gradovi gradovi)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Gradovi.Add(gradovi);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = gradovi.GradID }, gradovi);
        }

        // DELETE: api/Gradovi/5
        [ResponseType(typeof(Gradovi))]
        public IHttpActionResult DeleteGradovi(int id)
        {
            Gradovi gradovi = db.Gradovi.Find(id);
            if (gradovi == null)
            {
                return NotFound();
            }

            db.Gradovi.Remove(gradovi);
            db.SaveChanges();

            return Ok(gradovi);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool GradoviExists(int id)
        {
            return db.Gradovi.Count(e => e.GradID == id) > 0;
        }
    }
}