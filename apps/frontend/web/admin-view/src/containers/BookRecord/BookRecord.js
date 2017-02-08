/**
 * Created by ksb on 16. 11. 10.
 */
import React, {PropTypes, Component} from 'react';
import {Grid, Panel, Button, FormGroup, InputGroup, FormControl, DropdownButton, MenuItem, Glyphicon} from 'react-bootstrap';
import {connect} from 'react-redux';
import {asyncConnect} from 'redux-connect';
import {push as pushState} from 'react-router-redux';
import {loadSearchStatus, search} from 'redux/reducers/bookRecord';

@asyncConnect([
    {
        promise: ({store : {dispatch}}) => {
            return dispatch(loadSearchStatus());
        }
    }
])
@connect(
    state => ({
        loading: state.bookRecord.loading,
        searchStatus: state.bookRecord.searchStatus
    }),
    {pushState, search}
)
export default class BookRecord extends Component {
    static propTypes = {
        children: PropTypes.object.isRequired
    }

    state = {
        term: this.props.location.query.term ? this.props.location.query.term : "",
        type: this.props.location.query.type ? this.props.location.query.type : this.props.searchStatus[0]
    }

    onChangeSearchText(e){
        this.setState({term:e.target.value});
    }

    onChangeSearchType(type){
        this.setState({type:type});
    }

    onKeyUpSearchText(e){
        if (e.key === 'Enter') {
            this.onClickSearchButton();
        }
    }

    onClickSearchButton(){
        const {pushState, search} = this.props;
        const {term, type: {code}} = this.state;
        search(term, code);
        pushState('/bookRecord?term=' + term + '&type=' + code);
    }

    render() {
        const {loading, searchStatus, pushState} = this.props;
        const {props: {route: {path}}} = this.props.children;
        const searchDiv = {width:'320px'};
        const searchWrapperDiv = {clear:'both', marginTop:'25px'};
        const searchFormGroup = {marginBottom: 0};

        const title = (
            <div className="clearfix">
                <h1 className="pull-left panel-title">
                    책기록
                    {path && path === 'form' && ' 등록'}
                    {path && path === 'form/:idx' && ' 수정'}
                </h1>
                {
                    (!path || path === 'page/:page') &&
                    <Button disabled={loading} className="pull-right" onClick={() => pushState('/bookRecord/form')}>
                        등록
                    </Button>
                }
                <div style={searchWrapperDiv}>
                    {
                        (!path || path === 'page/:page') &&
                        <div className="pull-right" style={searchDiv}>
                            <FormGroup style={searchFormGroup}>
                                <InputGroup>
                                    <DropdownButton
                                        componentClass={InputGroup.Button}
                                        id="input-dropdown-addon"
                                        title={this.state.type.value}>
                                        {
                                            searchStatus && searchStatus.length>0 && searchStatus.map((status, i) =>
                                                <MenuItem key={i} onClick={() => this.onChangeSearchType(status)}>
                                                    {status.value}
                                                </MenuItem>
                                            )
                                        }
                                    </DropdownButton>
                                    <FormControl type="text" value={this.state.term}
                                                 onChange={this.onChangeSearchText.bind(this)}
                                                 onKeyUp={this.onKeyUpSearchText.bind(this)}
                                                 placeholder="Search..."/>
                                    <InputGroup.Button>
                                        <Button disabled={loading} onClick={this.onClickSearchButton.bind(this)}>
                                            <Glyphicon glyph="search" />
                                        </Button>
                                    </InputGroup.Button>
                                </InputGroup>
                            </FormGroup>
                        </div>
                    }
                </div>
            </div>
        );

        return (
            <Grid>
                <Panel header={title}>
                    {this.props.children}
                </Panel>
            </Grid>
        )
    }
}