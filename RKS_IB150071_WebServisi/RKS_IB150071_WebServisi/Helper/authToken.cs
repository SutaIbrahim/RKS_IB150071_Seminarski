using RKS_IB150071_WebServisi.Models;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Web;
using System.Web.Http;

namespace RKS_IB150071_WebServisi.Helper
{
    public class authToken: ApiController // svi kontroleri nasljedjuju authToken
    {
        private RKS_150071Entities db = new RKS_150071Entities();

        protected static string GetAuthToken()
        {
            string authToken = null;
            NameValueCollection headers = HttpContext.Current.Request.Headers;

            if (headers["authToken"] != null)
                authToken = headers["authToken"];

            return authToken;
        }

        protected bool ProvjeriValidnostTokena()
        {
            AutorizacijskiToken TokenCheck = db.GetTokenPoVrijednosti( GetAuthToken() ).FirstOrDefault();

            if (TokenCheck != null)
            {
                if (TokenCheck.VrijemeEvidentiranja >= DateTime.Now.AddDays(-2)) // token moze biti star 2 dana
                { 
                    return true;
                }
            }

            return false;
        }

        protected void IzbrisiToken()
        {
            AutorizacijskiToken TokenCheck = db.GetTokenPoVrijednosti(GetAuthToken()).FirstOrDefault();

            if (TokenCheck != null)
            {
                db.AutorizacijskiToken.Remove(TokenCheck);
                db.SaveChanges();
            }
        }


    }
}