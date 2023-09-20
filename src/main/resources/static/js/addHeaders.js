document.body.addEventListener('htmx:configRequest', function (evt) {
  var buttonElement = evt.detail.elt;
  var csrf = buttonElement.getAttribute('data-token');
  var headerName = buttonElement.getAttribute('data-header-name');
  evt.detail.headers[headerName] = csrf;
});