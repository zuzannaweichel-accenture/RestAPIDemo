using System;
using System.IO;
using System.Net;
using System.Web;

namespace RestService
{
    public class MapBoxService
    {
        private string _baseUrl = "https://api.mapbox.com/geocoding/v5/mapbox.places/";
        public string MakeCall(string searchAddress, string apiToken)
        {
            var encodedAddress = HttpUtility.UrlEncode(searchAddress);
            
            var request = WebRequest.CreateHttp($"{_baseUrl}{encodedAddress}.json?access_token={apiToken}");

            HttpWebResponse response = null;
            
            try
            {
                response = (HttpWebResponse)request.GetResponse();
            }
            catch (WebException we)
            {
                return we.Message;
            }

            if (response.StatusCode == HttpStatusCode.OK)
            {
                var contentStream = new StreamReader(response.GetResponseStream());

                return contentStream.ReadToEnd();
            }

            return response.StatusDescription;
        }
    }
}