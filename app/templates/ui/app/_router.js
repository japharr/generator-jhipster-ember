import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route('login');
  this.route('index', {
    path: '/'
  });
  this.resource('loggers', function() {});
  this.resource('auditEvents', function() {});
  this.resource('users', function() {
    this.route('new');
    this.route('edit', {path: '/:id'});
  });
});

export default Router;