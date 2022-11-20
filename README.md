<h1 align="center"> Foptility </h1>
<h3 align="center"> Simple BFF that generates pdf files starting from a set of xslt templates and a collection of parameters specifically formatted. </h3>

<p align="center" >
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />
  <img src="https://img.shields.io/badge/Apache%20FOP-FF3399?style=for-the-badge&logo=Apache&logoColor=white" />
</p>

---

Rest API that exposes method to perform PDF generation given its content. This application only generates PDF associated with product tracking workflow relative to Italian land production of small enterprises. 

Currently are handled only 6 types of documents that are the following:

<ul>
  <li>Tracciabilità piante e semi</li>
  <li>Tracciabilità materie prime</li>
  <li>Checlist materie prime</li>
  <li>Tracciabilità prodotto finito (lavorazione)</li>
  <li>Controllo pulizie</li>
  <li>Tracciabilità prodotto finito</li>
</ul>

<h2> How to use</h2>
Execute a <strong> POST </strong> request to the exposed endpoint: <i> https://foptility.herokuapp.com/foptility/transform/json</i> and provide a <strong>JSON</strong> well-formatted containing all required parameters.
The provided JSON must be wrapped in <i>parameters</i> object and must contain:
<ol>
  <li>Filename associated with the required PDF (ChecklistMateriePrime.pdf, ControlloPulizie.pdf, LavorazioneProdottoFinito.pdf, TracciabilitaMateriePrime.pdf, TracciabilitaPianteESemi.pdf, TracciabilitaProdottoFinito.pdf).</li>
  <li>Information about the footer, composed of two fields: title and subtitle (can be left empty).</li>
  <li>A list of items containing all custom text to inject in the PDF. This can vary from template to template.</li>
  <li>A field <i>productInfo</i> that contains generic properties. Only necessary for specific templates.</li>
</ol>

Following is a snipped example that can be executed with a <strong>REST Client<strong>.

```
POST https://foptility.herokuapp.com/foptility/transform/json
Content-Type: application/json

{
    "parameters": {
        "filename": "TracciabilitaMateriePrime.pdf",
        "footer": {
          "title": "Enterprise Name",
          "subtitle": "Enterprise subtitle"
        },
        "items": {
          "plants": "Tomatoes",
          "origin": "Autoproduzione',
          "lot": "123ABC",
          "isCompliant": true,
          "kg": "12",
        }
    }
}
```
<i> Uses Java11+ and Maven 3.6+.</i> 
