/**
 * Created by ksb on 16. 11. 19.
 */
import React, {PropTypes, Component} from 'react';
import {FormControl, FormGroup, ControlLabel, HelpBlock, Row, Col, Button, Thumbnail} from 'react-bootstrap';
import Sortable, {sortable, SortableItemMixin} from 'react-anything-sortable';

const SortItem = React.createClass({
    mixins: [SortableItemMixin],

    getDefaultProps() {
        return {
            className: 'sort-item'
        };
    },

    render() {
        const { className, children } = this.props;
        return this.renderWithSortable(
            <Col xs={12} sm={12} md={6} lg={4} className={className}>
                {children}
            </Col>
        );
    }
});

export default class Image extends Component {

    handleSort(sortedArray) {
        const {index, blockState, setBlockState} = this.props;
        setBlockState(index, {...blockState, imageList:sortedArray.map((image, i) => {
            return {...image, sortOrder: i+1};
        })});
    }

    render() {
        const {index, block: {caption}, blockState, setBlockState} = this.props;

        return (
            <div>
                <FormGroup style={{margin:"0 0 15px 0"}}>
                    <FormControl type="text" placeholder="캡션" {...caption} />
                    {caption.touched && caption.error && <HelpBlock>{caption.error}</HelpBlock>}
                </FormGroup>
                <FormGroup>
                    <ControlLabel>이미지</ControlLabel>
                    <FormControl type="file" onChange={(e) => {
                        e.preventDefault();
                        const file = e.target.files[0];
                        const fileExt = file.name.slice(file.name.indexOf(".") + 1).toLowerCase();
                        if(fileExt == "jpg" || fileExt == "png" ||  fileExt == "gif" ||  fileExt == "bmp"){
                            let reader = new FileReader();
                            reader.onloadend = () => {
                                setBlockState(index, {...blockState, imageList:[...blockState.imageList,
                                    {imageIdx: 0, imageUrl: reader.result, sortOrder: blockState.imageList.length+1, file:file}]});
                            };
                            reader.readAsDataURL(file);
                        } else {
                            console.log(fileExt + "올바른 파일이 아닙니다.");
                        }
                    }}/>
                </FormGroup>
                <Row>
                    <Sortable onSort={::this.handleSort} dynamic sortHandle="handle">
                        {
                            blockState.imageList && blockState.imageList.length>0 && blockState.imageList.map((image, i) =>
                                <SortItem key={i} sortData={image}>
                                    <Thumbnail src={image.imageUrl} alt="">
                                        <Button bsStyle="primary" onClick={() =>
                                            setBlockState(index, {...blockState, imageList:blockState.imageList.filter((image, j) => i !== j)})}>
                                            삭제
                                        </Button>
                                        <Button className="handle">이동</Button>
                                    </Thumbnail>
                                </SortItem>
                            )
                        }
                    </Sortable>
                </Row>
            </div>
        )
    }

}