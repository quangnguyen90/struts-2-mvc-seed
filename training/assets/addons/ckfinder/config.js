/*
Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
For licensing, see license.txt or http://cksource.com/ckfinder/license
*/

CKFinder.customConfig = function( config )
{
	// Define changes to default configuration here.
	// For the list of available options, check:
	// http://docs.cksource.com/ckfinder_2.x_api/symbols/CKFinder.config.html

	// Sample configuration options:
	// config.uiColor = '#BDE31E';
	// config.language = 'fr';
	// config.removePlugins = 'basket';

	// Enable js plugins
	// Ref: http://docs.cksource.com/CKFinder_2.x/Developers_Guide/Java/Plugins
	config.extraPlugins = 'dummy';
	config.extraPlugins = 'fileeditor';
	config.extraPlugins = 'flashupload';
	config.extraPlugins = 'gallery';
	config.extraPlugins = 'imageresize';
};
